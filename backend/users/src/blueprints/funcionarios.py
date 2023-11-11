from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.list_funcionarios import ListFuncionarios
from ..models.user import User, Base
from ..commands.create_funcionarios import CreateFuncionario
from ..session import Session, engine
import bcrypt

session = Session(bind=engine)
funcionarios_blueprint = Blueprint("funcionario", __name__)


@funcionarios_blueprint.route("/users/funcionarios/ping", methods=["GET"])
def ping():
    return "pong"


@funcionarios_blueprint.route("/users/funcionarios", methods=["POST"])
def create():
    user = CreateFuncionario(request.get_json()).execute()
    return jsonify(user), 201


@funcionarios_blueprint.route("/users/funcionarios", methods=["GET"])
def list():
    user = ListFuncionarios().execute()
    return jsonify(user), 201


@funcionarios_blueprint.route("/users/funcionarios/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
