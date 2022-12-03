(ns com.scarytom.aoc.utils
  (:require [clojure.java.io :as io]))

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