from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_user import CreateUser
from ..commands.create_admin import CreateAdmin
from ..commands.list_admins import ListAdmin
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


@users_blueprint.route("/users/agregar-admin", methods=["POST"])
def create_admin():
    user = CreateAdmin(request.get_json()).execute()
    return jsonify(user), 201


@users_blueprint.route("/users/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


@users_blueprint.route("/users/login", methods=["POST"])
def login():
    input_correo = request.get_json()["correo"]
    user = session.query(User).filter_by(correo=input_correo).first()
    if user is None:
        return jsonify({"message": "Usuario no existe"})
    encode_passsword = user.password  ## validación password por implementar
    user_contraseña = request.get_json()["contraseña"]
    usuario_correo = user.correo
    if (str(usuario_correo) == str(input_correo)) and (
        bcrypt.checkpw(
            str(user_contraseña).encode("utf8"), encode_passsword.encode("utf8")
        )
    ):
        user_id = user.id
        user_token = user.token
        return (
            jsonify(
                {
                    "message": "Usuario logueado con exito",
                    "id": user_id,
                    "token": user_token,
                }
            ),
            200,
        )
    else:
        return jsonify({"message": "Usuario no logueado"}), 401


@users_blueprint.route(
    "/users/asociar_candidato_equipo/<int:id_equipo>", methods=["POST"]
)
def asociar_candidato(id_equipo):
    asociar = CreateUser(request.get_json()).crear_candidato_equipo(id_equipo)
    return jsonify(asociar), 201


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization


@users_blueprint.route("/users/admins", methods=["GET"])
def get_admins():
    admins = ListAdmin().execute()
    return admins, 200
