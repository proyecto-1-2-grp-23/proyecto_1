from flask import Flask, jsonify, request, Blueprint
from ..session import Session, engine
from ..commands.list_candidatos import ListCandidato

session = Session(bind=engine)
candidatos_blueprint = Blueprint("candidatos", __name__)


@candidatos_blueprint.route("/users/candidatos/ping", methods=["GET"])
def ping():
    return "pongs"


@candidatos_blueprint.route("/users/candidatos", methods=["GET"])
def list():
    candidatos = ListCandidato().execute()
    return jsonify(candidatos), 200

