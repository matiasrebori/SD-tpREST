import requests


def listarMovil():
    response = requests.get("http://localhost:8080/SD-tpREST/rest/LT")
    xd = response.json()
    print(xd)


def insertarMovil():
    url = 'http://localhost:8080/SD-tpREST/rest/LT/record'
    myobj = {"type":"tesla"}
    response = requests.post(url, json=myobj)
    print(response.text)


def insertarUbicacion():
    url = 'http://localhost:8080/SD-tpREST/rest/LT/recordLocation'
    myobj = \
        {
        "transporte": {"id": 10},
        "posX": 10,
        "posY": 15
        }
    response = requests.post(url, json=myobj)
    print(response.text)


def movilesCercanos():
    url = 'http://localhost:8080/SD-tpREST/rest/LT/near'
    myobj = {"posX": 2, "posY": 2, "radius": 16}
    response = requests.post(url, json=myobj)
    for x in response.json():
        print(x)


#listarMovil()
#insertarMovil()
#insertarUbicacion()
movilesCercanos()
#moviles cercanos a posx 2 posy 2 radio 15