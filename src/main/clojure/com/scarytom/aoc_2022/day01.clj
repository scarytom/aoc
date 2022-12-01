(ns com.scarytom.aoc-2022.day01
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defn read-elf-calories []
  (with-open [reader (io/reader (io/resource "day01_1.txt"))]
    (->> (line-seq reader)
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