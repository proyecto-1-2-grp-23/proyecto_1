from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_equipo import CreateEquipo


equipo_blueprint = Blueprint("equipo", __name__)



@equipo_blueprint.route("/equipo/ping", methods=["GET"])
def ping():
    return jsonify({"ping": "pong"})

@equipo_blueprint.route("/equipo/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})

@equipo_blueprint.route("/equipo", methods=["POST"])
def create():
    equipo = CreateEquipo(request.get_json()).execute()
    return jsonify(equipo), 201

