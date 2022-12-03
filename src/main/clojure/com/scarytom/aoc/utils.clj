(ns com.scarytom.aoc.utils
  (:require [clojure.java.io :as io])
  (:import [java.net URI]
           [java.net.http HttpClient HttpRequest HttpResponse$BodyHandlers]))

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
  (let [input-path (str "inputs/" year "/day" (format "%02d" day) ".txt")]
    (with-open [reader (io/reader (io/resource input-path))]
      (f (line-seq reader)))))

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