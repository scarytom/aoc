(ns com.scarytom.aoc-2022.day03
  (:require [clojure.java.io :as io]
            [clojure.set :as set]))

(defn only [[head & tail :as coll]]
  (if (and head (empty? tail))
    head
    (throw (IllegalStateException. (str "Coll was not 1 item " (vec coll))))))

(defn priority [item]
  (if (-> item (int) (> 90))
    (-> item (int) (- 96))
    (-> item (int) (- 38))))

(defn sack-contents
  ([]
   (with-open [reader (io/reader (io/resource "day03_1.txt"))]
     (sack-contents (line-seq reader))))
  ([lines]
   (mapv (fn [line]
           (let [mid-point (/ (count line) 2)]
             [(seq (subs line 0 mid-point)) (seq (subs line mid-point))]))
         lines)))

(defn prioritise-sack [[compartment-1 compartment-2]]
  (->> (set/intersection (set compartment-1) (set compartment-2))
       (map priority)
       (reduce +)))

(defn part-1 []
  (->> (sack-contents)
       (map prioritise-sack)
       (reduce +)))

(defn find-badge [sacks]
  (->> sacks
       (map (comp set (partial apply concat)))
       (apply set/intersection)
       (only)))

(defn part-2 []
  (->> (sack-contents)
       (partition 3)
       (map find-badge)
       (map priority)
       (reduce +)))
