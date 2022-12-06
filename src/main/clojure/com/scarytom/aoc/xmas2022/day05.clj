(ns com.scarytom.aoc.xmas2022.day05
  (:require [clojure.string :as string]
            [com.scarytom.aoc.utils :as utils]))

(defn decode-stacks [lines]
  (let [[_ & lines] (reverse lines)]
    (->> lines
         (map #(take-nth 4 (drop 1 %)))
         (reduce (fn [stacks row]
                   (map (partial utils/conj-when #(not= \space %)) stacks row))
                 (repeat nil))
         (vec))))

(def move-regex #"move (\d+) from (\d+) to (\d+)")
(defn decode-moves [lines]
  (mapv #(let [[_ a b c] (re-matches move-regex %)]
          [(utils/to-int a) (dec (utils/to-int b)) (dec (utils/to-int c))]) lines))

(defn read-inputs
  ([]
   (utils/read-input-file 2022 5 read-inputs))
  ([lines]
   (let [[stacks _ moves] (partition-by string/blank? lines)]
     [(decode-stacks stacks) (decode-moves moves)])))

(defn perform-moves [model stacks moves]
  (reduce (fn [stacks [num from to]]
            (let [adjust-fn (if (= :9000 model) reverse identity)]
              (-> stacks
                  (update from (partial drop num))
                  (update to (partial concat (adjust-fn (take num (get stacks from))))))))
          stacks moves))

(defn part-1 []
  (let [[stacks moves] (read-inputs)]
    (apply str (mapv first (perform-moves :9000 stacks moves)))))

(defn part-2 []
  (let [[stacks moves] (read-inputs)]
    (apply str (mapv first (perform-moves :9001 stacks moves)))))