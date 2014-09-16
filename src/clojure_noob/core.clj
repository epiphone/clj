(ns clojure-noob.core
  (:require [lanterna.screen :as s])
  (:require [clojure-noob.display :refer [draw, show-start-menu]])
  (:require [clojure-noob.entities :refer [initial-state]])
  )

(def width 100)
(def height 24)

(defn update-tile [tile key-pressed]
  (let [actions (:actions tile)]
    ((apply comp actions) tile key-pressed)))

(defn update [state key-pressed]
  (let [new-turn (-> state :turn inc)
        new-tiles (map #(update-tile % key-pressed) (:tiles state))]
    (assoc state :turn new-turn :tiles new-tiles)))

(defn game-loop [state scr]
  (let [key-pressed (s/get-key-blocking scr)]
    (if (= :escape key-pressed)
      nil
      (let [new-state (update state key-pressed)]
        (draw new-state scr)
        (game-loop new-state scr)))))

(defn start-game [scr]
  (do
    (draw initial-state scr)
    (game-loop initial-state scr)))

(defn print-key [screen]
  (println (s/get-key-blocking screen)))

(defn -main [screen-type]
  (let [screen (s/get-screen screen-type {:cols width :rows height})]
    (s/in-screen screen
                 (show-start-menu screen)
                 (start-game screen))))

(-main :swing)
