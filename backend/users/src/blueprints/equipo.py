from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_equipo import CreateEquipo
from ..commands.list_equipo import ListEquipo
from ..commands.create_equipo_candidato import CreateEquipoCandidato
from ..commands.list_candidatos_equipo import ListCandidatosEquipo


equipo_blueprint = Blueprint("equipo", __name__)


@equipo_blueprint.route("/users/equipo/ping", methods=["GET"])
def ping():
    return jsonify({"ping": "pong"})


@equipo_blueprint.route("/users/equipo/crear-equipos", methods=["POST"])
def create():
    equipo = CreateEquipo(request.get_json()).execute()
    return jsonify(equipo), 201


@equipo_blueprint.route("/users/equipo/listar-equipos", methods=["GET"])
def listarTodos():
    equipo = ListEquipo().execute()
    return jsonify(equipo), 200


@equipo_blueprint.route("/users/equipo/equipo-candidato", methods=["POST"])
def createEquipoCandidato():
    equipo = CreateEquipoCandidato(request.get_json()).execute()
    return jsonify(equipo), 201


@equipo_blueprint.route("/users/equipo/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


@equipo_blueprint.route(
    "/users/equipo/listar-candidatos/<int:idEquipo>", methods=["GET"]
)
def listarCandidatoEquipo(idEquipo):
    equipo = ListCandidatosEquipo(idEquipo).execute()
    return jsonify(equipo), 200
