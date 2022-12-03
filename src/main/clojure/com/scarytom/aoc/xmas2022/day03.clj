(ns com.scarytom.aoc.xmas2022.day03
  (:require [clojure.set :as set]
            [com.scarytom.aoc.utils :as utils]))

(defn priority [item]
  (if (-> item (int) (> 90))
    (-> item (int) (- 96))
    (-> item (int) (- 38))))

(defn prioritise [things]
  (->> things
       (map set)
       (apply set/intersection)
       (utils/only)
       priority))

(defn part-1 []
  (utils/read-input-file 2022 3 #(->> %
                                      (map (partial utils/chop 2))
                                      (map prioritise)
                                      (reduce +))))

(defn part-2 []
  (utils/read-input-file 2022 3 #(->> %
                                      (partition 3)
                                      (map prioritise)
                                      (reduce +))))
