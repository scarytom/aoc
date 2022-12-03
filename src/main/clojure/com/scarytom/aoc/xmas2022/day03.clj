(ns com.scarytom.aoc.xmas2022.day03
  (:require [clojure.set :as set]
            [com.scarytom.aoc.utils :as utils]))

(defn priority [item]
  (if (-> item (int) (> 90))
    (-> item (int) (- 96))
    (-> item (int) (- 38))))

(defn sack-contents
  ([]
   (utils/read-input-file 2022 3 sack-contents))
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
       (utils/only)))

(defn part-2 []
  (->> (sack-contents)
       (partition 3)
       (map find-badge)
       (map priority)
       (reduce +)))
