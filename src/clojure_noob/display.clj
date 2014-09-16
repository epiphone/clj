(ns clojure-noob.display
  (:require [lanterna.screen :as s]))

(def symbols
  {:player "x"
   :baddie "o"
   :wall "S"})

(def colors
  {:player {:fg :yellow}
   :baddie {:fg :red}
   :wall {:fg :black :bg :grey}})


(defn clear-and-redraw [screen & fs]
  (do
    (s/clear screen)
    (doseq [f fs] (f))
    (s/redraw screen)))

(defn show-start-menu [screen]
  (do
    (clear-and-redraw screen
                      #(s/put-string screen 10 10 "Welcome to the Caves of Clojure!")
                      #(s/put-string screen 10 11 "Press any key to start..."))
    (s/get-key-blocking screen)))


(defn draw-tile [scr {:keys [x y] :as tile}]
  (let [character (-> tile :type symbols)
        colors (-> tile :type colors)]
    (println "asd")
    (s/put-string scr x y character colors)))

(defn draw [state scr]
  (let [[w h] (s/get-size scr)]
    (clear-and-redraw scr
                    #(map (partial draw-tile scr) (:tiles state))
                    #(s/put-string scr 0 (dec h) (str "turn " (:turn state))))))

