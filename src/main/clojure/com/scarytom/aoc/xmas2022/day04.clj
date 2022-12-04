(ns com.scarytom.aoc.xmas2022.day04
  (:require [com.scarytom.aoc.utils :as utils]))

(def assignment-pattern #"(\d+)-(\d+),(\d+)-(\d+)")

(defn read-assignment [line]
  (->> line
       (re-matches assignment-pattern)
       (rest)
       (map utils/to-int)))

(defn read-assignments
  ([]
   (utils/read-input-file 2022 4 read-assignments))
  ([lines]
   (mapv read-assignment lines)))

(defn fully-contains? [[a b c d]]
  (and (or (>= c a) (>= d b))
       (or (<= c a) (<= d b))))

(defn overlaps? [[a b c d]]
  (and (>= d a) (<= c b)))

(defn part-1 []
  (->> (read-assignments)
       (filter fully-contains?)
       count))

(defn part-2 []
  (->> (read-assignments)
       (filter overlaps?)
       count))
