(ns com.scarytom.aoc.xmas2021.day01
  (:require [com.scarytom.aoc.utils :as utils]))

(defn count-increases [ints]
  (->> (map vector ints (drop 1 ints))
       (filter (fn [[a b]] (> b a)))
       (count)))

(defn part-1 []
  (utils/read-input-file 2021 1 #(count-increases (map utils/to-int %))))

(defn part-2 []
  (utils/read-input-file 2021 1 (fn [lines]
                                  (let [ints (map utils/to-int lines)]
                                    (->> (map vector ints (drop 1 ints) (drop 2 ints))
                                         (map #(reduce + %))
                                         (count-increases))))))
