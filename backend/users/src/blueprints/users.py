from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_user import CreateUser
from ..models.user import User, Base
from ..session import Session, engine
import bcrypt

session = Session(bind=engine)
users_blueprint = Blueprint("users", __name__)


@users_blueprint.route("/users/ping", methods=["GET"])
def ping():
    return "pong"


@users_blueprint.route("/users", methods=["POST"])
def create():
    user = CreateUser(request.get_json()).execute()
    return jsonify(user), 201


@users_blueprint.route("/users/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


@users_blueprint.route("/users/login", methods=["POST"])
def login():
    token_user = (
        session.query(User).filter_by(correo=request.get_json()["correo"]).first()
    )
    token = token_user.token
    encode_passsword = token_user.password  ## validación password por implementar
    tokten_Header = auth_token()
    if str(tokten_Header) == str(token) and bcrypt.checkpw(
        request.get_json()["contraseña"].encode("utf8"), encode_passsword.encode("utf8")
    ):
        return jsonify({"message": "Login con exito"}), 200
    else:
        return jsonify({"message": "Usuario no loegueado"}), 401


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
