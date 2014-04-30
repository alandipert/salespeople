#!/usr/bin/env boot

#tailrecursion.boot.core/version "2.3.1"

(set-env!
 :dependencies '[[tailrecursion/boot.task   "2.1.2"]
                 [tailrecursion/hoplon      "5.7.0"]
                 [tailrecursion/boot.notify "2.0.0-SNAPSHOT"]
                 [tailrecursion/boot.ring   "0.1.0-SNAPSHOT"]
                 [org.clojure/clojurescript "0.0-2156"]]
 :src-paths    #{"src"}
 :out-path     "resources/public")

(add-sync! (get-env :out-path) #{"resources/assets"})

(require
 '[tailrecursion.hoplon.boot      :refer :all]
 '[tailrecursion.boot.task        :refer :all]
 '[tailrecursion.boot.task.notify :refer [hear]]
 '[tailrecursion.boot.task.ring   :refer [dev-server]])

(deftask production
  "Build for production"
  [& args]
  (comp (hoplon {:optimizations :advanced})))

(deftask development
  "Build for development"
  [& args]
  (comp
   (watch)
   #_(hear)
   (hoplon {:prerender false})
   (dev-server)))
