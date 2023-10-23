from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_empresa import CreateEmpresa

empresa_blueprint = Blueprint("empresa", __name__)


@empresa_blueprint.route("/empresa/ping", methods=["GET"])
def ping():
    return "pong"


@empresa_blueprint.route("/empresa", methods=["POST"])
def create():
    user = CreateEmpresa(request.get_json()).execute()
    return jsonify(user), 201


@empresa_blueprint.route("/empresa/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
