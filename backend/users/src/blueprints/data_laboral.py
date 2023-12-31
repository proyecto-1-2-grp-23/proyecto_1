from ..commands.list_data_laboral import ListDataLaboral
from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_user import CreateUser
from ..models.user import User, Base
from ..commands.data_laboral_create import CreateLaboral
from ..session import Session, engine
import bcrypt

session = Session(bind=engine)
laboral_data_blueprint = Blueprint("laboral", __name__)


@laboral_data_blueprint.route("/users/dataLaboral/ping", methods=["GET"])
def ping():
    return "pong"


@laboral_data_blueprint.route("/users/dataLaboral", methods=["POST"])
def create():
    user = CreateLaboral(request.get_json()).execute()
    return jsonify(user), 201

@laboral_data_blueprint.route("/users/dataLaboral", methods=["GET"])
def get_datalaboral():
    equipo = ListDataLaboral().execute()
    return jsonify(equipo), 200

@laboral_data_blueprint.route("/users/dataLaboral/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
