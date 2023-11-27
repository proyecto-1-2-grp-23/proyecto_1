import sys


sys.path.append(".")
from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands import (
    CreatePregunta,
    ListPreguntas,
    ListPreguntasPorProyecto,
    RegistrarResultados,
    ObtenerResultados,
)
from ..commands.create_envio_respuestas import CreatePreguntaPorCandidato
from ..commands.create_desempeno_habilidad import CreateDesempeño
pruebas_blueprint = Blueprint("pruebas", __name__)


@pruebas_blueprint.route("/pruebas/ping", methods=["GET"])
def ping():
    return "pong"


@pruebas_blueprint.route("/pruebas/preguntas", methods=["POST"])
def post_preguntas():
    pregunta = CreatePregunta(request.get_json()).execute()
    return jsonify(pregunta), 201


@pruebas_blueprint.route("/pruebas/preguntas", methods=["GET"])
def list_preguntas():
    preguntas = ListPreguntas().execute()
    return jsonify(preguntas), 200


@pruebas_blueprint.route("/pruebas/preguntas/proyectos/<int:id>", methods=["GET"])
def list_preguntas_por_proyecto(id):
    preguntas = ListPreguntasPorProyecto(id).execute()
    return jsonify(preguntas), 200


@pruebas_blueprint.route("/pruebas/respuesta-enviada", methods=["POST"])
def create_respuesta_por_candidato():
    envios = CreatePreguntaPorCandidato(request.get_json()).execute()
    return jsonify(envios), 200


@pruebas_blueprint.route("/pruebas/registrar-resultado/<int:idEnvio>", methods=["PUT"])
def registrar_resultados_de_envio(idEnvio):
    envio = RegistrarResultados(request.get_json(), idEnvio).execute()
    if not envio:
        return {}, 404
    return jsonify(envio), 200


@pruebas_blueprint.route(
    "/pruebas/proyectos/<int:idProyecto>/candidatos/<int:idCandidato>", methods=["GET"]
)
def obtener_resultados_por_proyecto_por_candidato(idProyecto, idCandidato):
    envio = ObtenerResultados(idProyecto, idCandidato).execute()
    if not envio:
        return {}, 404
    return jsonify(envio), 200


@pruebas_blueprint.route("/pruebas/evaluacion_desempeño", methods=["POST"])
def crear_evaluacion_desempeño():
    evaluacion = CreateDesempeño(request.get_json()).execute()
    return jsonify(evaluacion), 201


@pruebas_blueprint.route("/pruebas/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
