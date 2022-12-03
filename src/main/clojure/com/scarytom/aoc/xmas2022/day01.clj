(ns com.scarytom.aoc.xmas2022.day01
  (:require [clojure.string :as string]
            [com.scarytom.aoc.utils :as utils]))

(defn read-elf-calories
  ([]
   (utils/read-input-file 2022 1 read-elf-calories))
  ([lines]
   (->> lines
        (map #(when-not (string/blank? %)
                (Integer/parseInt %)))
        (partition-by nil?)
        (take-nth 2)
        (mapv #(reduce + %)))))

(defn part-1 []
  (apply max (read-elf-calories)))

(defn part-2 []
  (->> (read-elf-calories)
       (sort >)
       (take 3)
       (reduce +)))