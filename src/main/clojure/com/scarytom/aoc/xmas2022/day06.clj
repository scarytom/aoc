(ns com.scarytom.aoc.xmas2022.day06
  (:require [clojure.core.cache :as cache]
            [com.scarytom.aoc.utils :as utils]))

(defn find-marker-index [marker-size characters]
  (reduce (fn [acc character]
            (let [idx (or (some->> acc keys seq (apply max) inc) 0)
                  new-acc (assoc acc idx character)]
              (if (-> acc vals distinct count (= marker-size))
                (reduced idx)
                new-acc)))
          (cache/fifo-cache-factory {} :threshold marker-size)
          characters))

(defn part-1 []
  (utils/read-input-file 2022 6 #(find-marker-index 4 (utils/only %))))

(defn part-2 []
  (utils/read-input-file 2022 6 #(find-marker-index 14 (utils/only %))))