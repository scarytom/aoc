(ns com.scarytom.aoc.xmas2022.day08
  (:require
    [com.scarytom.aoc.utils :as utils]))

(defrecord Tree [height position]
  Comparable
  (compareTo [this other]
    (.compareTo (:height this) (:height other))))

(defn read-grid [lines]
  (for [[y heights] (map vector (range) lines)]
    (for [[x height] (map vector (range) heights)]
      (->Tree (utils/char-to-int height) [x y]))))

(defn find-visible-trees [lines]
  (->> (read-grid lines)
       (utils/explode-grid)
       (reduce (fn [visible-trees line-of-sight]
                 (->> line-of-sight
                      (utils/filter-ascending)
                      (map :position)
                      (reduce conj visible-trees)))
               #{})))

(defn part-1 []
  (utils/read-input-file 2022 8 #(count (find-visible-trees %))))

(defn score [[x & xs]]
  (min
    (count xs)
    (inc (count (take-while #(pos? (compare x %)) xs)))))

(defn part-2 []
  (utils/read-input-file 2022 8 (fn [lines]
                                  (->> (read-grid lines)
                                       (utils/explode-grid)
                                       (reduce (fn [pos->score line-of-sight]
                                                 (loop [p->s pos->score
                                                        [tree & xs :as los] line-of-sight]
                                                   (if (seq xs)
                                                     (recur (update p->s (:position tree) (fnil * 1) (score los)) xs)
                                                     p->s))) {})
                                       (vals)
                                       (apply max)))))