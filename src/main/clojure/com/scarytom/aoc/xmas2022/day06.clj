(ns com.scarytom.aoc.xmas2022.day06
  (:require [com.scarytom.aoc.utils :as utils]))

(defn find-marker-index [marker-size characters]
  (reduce (fn [[buf idx] character]
            (let [new-buf (conj buf character)]
              (if (-> new-buf distinct count (= marker-size))
                (reduced idx)
                [new-buf (inc idx)])))
          [(utils/ring-buffer marker-size) 1]
          characters))

(defn part-1 []
  (utils/read-input-file 2022 6 #(find-marker-index 4 (utils/only %))))

(defn part-2 []
  (utils/read-input-file 2022 6 #(find-marker-index 14 (utils/only %))))