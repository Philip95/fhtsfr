# Argo
Aufgesetzt lokal mittels Tutorials https://argo-cd.readthedocs.io/en/stable/getting_started/
Port forwarding über ```kubectl port-forward svc/argocd-server -n argocd 8080:443```

Username: admin

Passwort: RLYg7QJMopgDn40A

Nicht geändert, weil nicht nötig in dem Fall, normalerweise schon.

Übersicht der Clusters:

Befehl:
```kubectl config get-contexts -o name```

Output:
```minikube```

### Register a cluster:

```.\argocd cluster add minikube```

Output:

```WARNING: This will create a service account `argocd-manager` on the cluster referenced by context `minikube` with full cluster level privileges. Do you want to continue [y/N]? y
time="2023-04-28T09:36:52+02:00" level=info msg="ServiceAccount \"argocd-manager\" created in namespace \"kube-system\""
time="2023-04-28T09:36:52+02:00" level=info msg="ClusterRole \"argocd-manager-role\" created"
time="2023-04-28T09:36:52+02:00" level=info msg="ClusterRoleBinding \"argocd-manager-role-binding\" created"
time="2023-04-28T09:36:57+02:00" level=info msg="Created bearer token secret for ServiceAccount \"argocd-manager\""
Cluster 'https://192.168.0.46:8443' added```

Cluster somit erfolgreich angelegt.

### Namespace setzen

Befehl:

```kubectl config set-context --current --namespace=argocd```

Output:

```Context "minikube" modified.```
### Create application
Befehl:

```.\argocd app create sfr --repo https://github.com/Philip95/fhtsfr.git --path sfr --dest-server https://kubernetes.default.svc --dest-namespace default```

Output:

```application 'sfr' created```

### Sync application

```.\argocd app get sfr```

```
Name:               argocd/sfr
Project:            default
Server:             https://kubernetes.default.svc
Namespace:          default
URL:                https://localhost:8080/applications/sfr
Repo:               https://github.com/Philip95/fhtsfr.git
Target:
Path:               sfr
SyncWindow:         Sync Allowed
Sync Policy:        <none>
Sync Status:        Synced to  (5ad0cb8)
Health Status:      Healthy
```

Die Dokumentation sieht eigentlich noch einen Schritt vor, um das Projekt zu syncen, allerdings scheint es hier schon
gesynct zu sein. Vielleicht passiert das nach einer gewissen Zeit automatisch, da ich auch währenddessen ArgoCD neu 
starten musste, da es mir den Port Forwarder zerschossen hat. Allerdings hat der Neustart dazu geführt, dass die Argo UI 
schlussendlich funktioniert. 

Schlussendlich hab ich gesehen, dass es keine Pods oder ähnliches gab und habe daher die yaml Files angelegt und mittels
```kubectl apply -f application.yaml``` manuell angestartet, was folgendes Ergebnis lieferte:

![img.png](img.png)

Der Fehler, wieso die Pods nicht gestartet werden konnten war/ist ein Fehler im Docker Image, den ich bis jetzt nicht beheben konnte.