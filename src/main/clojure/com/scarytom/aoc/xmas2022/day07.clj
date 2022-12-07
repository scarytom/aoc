(ns com.scarytom.aoc.xmas2022.day07
  (:require [com.scarytom.aoc.utils :as utils]))

(defn read-directory-tree [lines]
  (first (reduce (fn [[tree cwd] line]
                   (let [[_ a b] (re-matches #"(?:\$ )?(cd|ls|dir|\d+)(?: (.+))?" line)]
                     (case a
                       "cd" [tree (case b
                                    "/" '("/")
                                    ".." (rest cwd)
                                    (conj cwd b))]
                       "ls" [tree cwd]
                       "dir" [(assoc tree (conj cwd b) 0) cwd]

                       [(let [size (utils/to-int a)]
                          (loop [t tree
                                 [_ & xs :as d] cwd]
                            (cond-> (update t d (fnil + 0) size)
                                    xs (recur xs))))
                        cwd])))
                 [{} nil]
                 lines)))

(defn part-1 []
  (utils/read-input-file 2022 7 (fn [lines]
                                  (->> (read-directory-tree lines)
                                       (vals)
                                       (filter #(<= % 100000))
                                       (reduce +)))))

(defn part-2 []
  (utils/read-input-file 2022 7 (fn [lines]
                                  (let [directory-tree (read-directory-tree lines)
                                        total-size (get directory-tree '("/"))
                                        to-delete (- total-size 40000000)]
                                    (->> directory-tree
                                         (vals)
                                         (sort)
                                         (drop-while #(<= % to-delete))
                                         (first))))))