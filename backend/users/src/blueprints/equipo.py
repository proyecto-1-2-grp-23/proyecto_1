from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_equipo import CreateEquipo
from ..commands.list_equipo import ListEquipo


equipo_blueprint = Blueprint("equipo", __name__)

@equipo_blueprint.route("/users/equipo/ping", methods=["GET"])
def ping():
    return jsonify({"ping": "pong"})


@equipo_blueprint.route("/users/equipo", methods=["POST"])
def create():
    equipo = CreateEquipo(request.get_json()).execute()
    return jsonify(equipo), 201

@equipo_blueprint.route("/users/equipo/listar-equipos", methods=["GET"])
def listarTodos():
    equipo = ListEquipo().execute()
    return jsonify(equipo), 200

@equipo_blueprint.route("/users/equipo/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})

#@equipo_blueprint.route("/users/equipo/listar-equipos/<int:id_empresa>", methods=["GET"])
#def listar(id_empresa):
#    equipo = ListEquipo().Listar_id_empresa(id_empresa)
#    return jsonify(equipo), 200