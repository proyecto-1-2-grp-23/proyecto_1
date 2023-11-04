from flask import Flask, jsonify, request, Blueprint
from ..commands.create_entrevista import CreateEntrevista
from ..commands.get_entrevista import GetEntrevista
from ..models.entrevista import Entrevista
from ..session import Session, engine

session = Session(bind=engine)
entrevistas_blueprint = Blueprint("entrevistas", __name__)


@entrevistas_blueprint.route("/entrevistas/ping", methods=["GET"])
def ping():
    return "pong"


@entrevistas_blueprint.route("/entrevistas", methods=["POST"])
def create():
    user = CreateEntrevista(request.get_json()).execute()
    return jsonify(user), 201

@entrevistas_blueprint.route('/entrevistas/<id>', methods=['GET'])
def get(id):
    route = GetEntrevista(id).execute()
    return jsonify(route)

def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
