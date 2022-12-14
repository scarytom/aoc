(ns com.scarytom.aoc.xmas2022.day14
  (:require
    [clojure.set :as set]
    [clojure.string :as string]
    [com.scarytom.aoc.utils :as utils]))

(defn read-filled-coords [line]
  (->> (string/split line #" -> ")
       (map #(mapv utils/to-int (string/split % #",")))
       (partition 2 1)
       (map (fn [[[x1 y1] [x2 y2]]]
              (for [x (range (min x1 x2) (inc (max x1 x2)))
                    y (range (min y1 y2) (inc (max y1 y2)))]
                [x y])))
       (apply concat)
       (set)))

(defn read-structure [lines]
  (apply set/union (map read-filled-coords lines)))

(defn find-sand-rest-coord [structure origin floor endless-void?]
  (when-not (structure origin)
    (loop [[x y :as current-coord] origin]
      (if (>= y floor)
        (when-not endless-void? [x (dec floor)])
        (if-let [next-coord (first (remove structure [[x (inc y)] [(dec x) (inc y)] [(inc x) (inc y)]]))]
          (recur next-coord)
          current-coord)))))

(defn count-sand [endless-void? lines]
  (let [initial-structure (read-structure lines)
        floor (+ 2 (apply max (map second initial-structure)))
        final-structure (loop [structure initial-structure]
                          (if-let [coord (find-sand-rest-coord structure [500 0] floor endless-void?)]
                            (recur (conj structure coord))
                            structure))]
    (count (set/difference final-structure initial-structure))))

(defn part-1 []
  (utils/read-input-file 2022 14 (partial count-sand true)))

(defn part-2 []
  (utils/read-input-file 2022 14 (partial count-sand false)))