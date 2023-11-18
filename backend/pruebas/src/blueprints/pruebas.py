import sys

sys.path.append(".")

from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands import CreatePregunta, ListPreguntas, ListPreguntasPorProyecto

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


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
