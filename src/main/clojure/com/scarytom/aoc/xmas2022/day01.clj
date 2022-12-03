(ns com.scarytom.aoc.xmas2022.day01
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn read-elf-calories
  ([]
   (with-open [reader (io/reader (io/resource "inputs/2022/day01.txt"))]
     (read-elf-calories (line-seq reader))))
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
       (sort)
       (reverse)
       (take 3)
       (reduce +)))