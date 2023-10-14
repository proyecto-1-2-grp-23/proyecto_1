from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset

users_blueprint = Blueprint('users', __name__)


@users_blueprint.route('/auth/ping', methods=['GET'])
def ping():
    return 'pong'


@users_blueprint.route('/auth/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})


def auth_token():
    if 'Authorization' in request.headers:
        authorization = request.headers['Authorization']
    else:
        authorization = None
    return authorization