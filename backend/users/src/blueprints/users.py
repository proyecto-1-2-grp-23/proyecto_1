from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset
from ..commands.create_user import CreateUser

users_blueprint = Blueprint('users', __name__)


@users_blueprint.route('/users/ping', methods=['GET'])
def ping():
    return 'pong'


@users_blueprint.route('/users', methods=['POST'])
def create():
    user = CreateUser(request.get_json()).execute()
    return jsonify(user), 201


@users_blueprint.route('/users/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})


@users_blueprint.route('/users/login', methods=['POST'])
def auth_token():
    if 'Authorization' in request.headers:
        authorization = request.headers['Authorization']
    else:
        authorization = None
    return authorization