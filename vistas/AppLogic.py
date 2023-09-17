
from flask_restful import Resource
from flask import request,jsonify
import random

## Datos en BD


preguntas = [
            {"pregunta": "¿Es Python un luanguaje tipado?", "respuesta": "Si"},
            {"pregunta": "¿En Js se puede manipular documentos HTML y DOM?", "respuesta": "Si"},
            {"pregunta": "¿Que significa la abreviatura DOM?", "respuesta": "Document Object Model"}
            ]
class Prueba(Resource):
    
    def post(self):
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
    
    def get(self):

        pregunta_aleatoria = random.choice(preguntas)
        return jsonify(pregunta=pregunta_aleatoria['pregunta'])

class HealthCheck(Resource):
    def get(self):
        return "Pong", 200