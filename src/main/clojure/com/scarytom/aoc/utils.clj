(ns com.scarytom.aoc.utils
  (:require [clojure.java.io :as io])
  (:import [clojure.lang IPersistentStack Indexed PersistentQueue]
           [java.net URI]
           [java.net.http HttpClient HttpRequest HttpResponse$BodyHandlers]
           [java.util Collection]))

(defn download
  "Downloads an Advent of Code input file.  Relies on a session cookie, which can be obtained from the browser
  cookie store after logging in, and lasts for about a month."
  [url-string dest-file-str]
  (io/make-parents dest-file-str)
  (let [session-cookie (slurp "session-cookie.txt")
        request (-> (HttpRequest/newBuilder (URI. url-string))
                    (.header "cookie" (str "session=" session-cookie))
                    (.build))
        client (HttpClient/newHttpClient)]
    (with-open [input-stream (.body
                               (.send ^HttpClient client
                                      ^HttpRequest request
                                      (HttpResponse$BodyHandlers/ofInputStream)))
                output-stream (io/output-stream dest-file-str)]
      (io/copy input-stream output-stream))))

(defn obtain-input-file
  "Returns a resource for the input of the specified year and day, downloading it (and caching locally) if necessary."
  [year day]
  (let [input-path (str "inputs/" year "/day" (format "%02d" day) ".txt")]
    (if-let [resource (io/resource input-path)]
      resource
      (let [url-str (str "https://adventofcode.com/" year "/day/" day "/input")]
        (download url-str (str "src/main/resources/" input-path))
        (io/resource input-path)))))

(defn read-input-file
  "Reads the input file for the specified day and calls the specified function with a lazy seq of the lines
  within that file."
  [year day f]
  (with-open [reader (io/reader (obtain-input-file year day))]
    (f (line-seq reader))))

(defn only
  "Returns the only element in coll. If coll contains anything other than exactly one non-nil element, throws
   an exception."
  [[head & tail :as coll]]
  (if (and head (empty? tail))
    head
    (throw (IllegalStateException. (str "Coll was not 1 item " (vec coll))))))

(defn chop
  "chops a coll into n seqs of equal length"
  [n coll]
  (let [piece-len (/ (count coll) n)]
    (partition piece-len coll)))

(defn to-int
  "Converts a String to an int, giving a helpful exception upon failure"
  [s]
  (try
    (Integer/parseInt s)
    (catch Exception _
      (throw (IllegalStateException. (str "The string >" s "< is not an int"))))))

(defn char-to-int
  "Converts a char that is a digit into its numerical representation (e.g. \1 -> 1)"
  [char]
  {:pre  [(char? char)]
   :post [(<= 0 % 9)]}
  (- (int char) 48))

(defn filter-ascending
  "Returns a sequence of items from coll whose values are greater than the previous top
   value according to the specified comparator."
  ([coll]
   (filter-ascending compare coll))
  ([comparator coll]
   (second
     (reduce (fn [[biggest result :as acc] x]
               (if (or (empty? result)
                       (pos? (comparator x biggest)))
                 [x (conj result x)]
                 acc)) [nil []] coll))))

(defn explode-grid-columns
  "Accepts a grid defined as a vector of row vectors and returns that grid exploded out
   as a vector of rows vectors followed by column vectors"
  [rows]
  (concat rows (apply map list rows)))

(defn explode-grid
  "Accepts a grid defined as a vector of row vectors and returns that grid exploded out
   as a vector of rows vectors followed by column vectors, followed by both again with the
   vectors reversed"
  [rows]
  (let [rows-and-columns (explode-grid-columns rows)]
    (concat rows-and-columns (map reverse rows-and-columns))))

(defn conj-when
  "conj val onto coll if (pred val) returns true, otherwise returns coll unchanged"
  [pred coll val]
  (if (pred val)
    (conj coll val)
    coll))

(defn all-pairs
  "Returns a seq of tuples reflecting every possible pairing between the values in coll"
  [coll]
  (->> (repeat coll)
       (map drop (range 1 (count coll)))
       (mapcat #(map vector coll %))))

(defn simplify-ranges
  "Accepts a collection of tuples defining ranges, which are returned simplified
  by combining overlapping ranges"
  [coll]
  (reduce (fn [[[current-start current-end] & xs :as acc] [start-inclusive end-exclusive :as r]]
            (if (or (nil? current-end) (> start-inclusive current-end))
              (conj acc r)
              (conj xs [current-start (max end-exclusive current-end)])))
          nil
          (sort coll)))

(deftype RingBuf [capacity ^PersistentQueue buf]
  IPersistentStack
  (peek [_this]
    (peek buf))
  (pop [_this]
    (pop buf))
  (cons [_this x]
    (if (= (count buf) capacity)
      (RingBuf. capacity (pop (conj buf x)))
      (RingBuf. capacity (conj buf x))))
  (seq [_this]
    (seq buf))

  Collection
  (iterator [_this]
    (.iterator buf))
  (isEmpty [this]
    (empty? this))

  Indexed
  (nth [_this i]
    (nth buf i))
  (nth [_this i default]
    (nth buf i default)))

(defn ring-buffer [capacity]
  (RingBuf. capacity (PersistentQueue/EMPTY)))