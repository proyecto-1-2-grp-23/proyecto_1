
import sys

sys.path.append(".")

from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset

pruebas_blueprint = Blueprint("pruebas", __name__)


@pruebas_blueprint.route("/pruebas/ping", methods=["GET"])
def ping():
    return "pong"



def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
