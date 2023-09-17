from flask import Flask
from flask_restful import Api
from vistas.AppLogic import HealthCheck, Prueba

app = Flask(__name__)
@app.route('/')
def hello_world():
    return 'Hello World'

from flask import Flask, request, jsonify

app = Flask(__name__)

preguntas = [
            {"pregunta": "¿Es Python un luanguaje tipado?", "respuesta": "Si"},
            {"pregunta": "¿En Js se puede manipular documentos HTML y DOM?", "respuesta": "Si"},
            {"pregunta": "¿Que significa la abreviatura DOM?", "respuesta": "Document Object Model"}
]


@app.route('/Prueba', methods = ['POST'])
def hello_world():
    respuesta_usuario = request.json.get('respuesta', '').strip().lower()
    pregunta_actual = request.json.get('pregunta', '').strip()

    for pregunta in preguntas:
        if pregunta['pregunta'] == pregunta_actual:
            respuesta_correcta = pregunta['respuesta'].lower()
            print(respuesta_correcta)
            if respuesta_usuario == respuesta_correcta:
                return jsonify(resultado="Correcto")
            else:
                return jsonify(resultado="Incorrecto")

    return jsonify(resultado="Pregunta no encontrada")

@app.route('/')
def get():
        return "Ping", 200

if __name__ == "__main__":
        app.run()

#api = Api(app)
#api.add_resource(Prueba, '/Cuestionario')
#api.add_resource(HealthCheck, '/HealthCheck')

if __name__ == "__main__":
    app.run()
