from flask import Flask, jsonify, request, Blueprint
from ..commands.create_user import CreateUser
from ..commands.generate_token import GenerateToken
from ..commands.get_user import GetUser
from ..commands.reset import Reset
from ..commands.update_user import UpdateUser
import requests
import os
import json

users_blueprint = Blueprint('users', __name__)


@users_blueprint.route('/users', methods=['POST'])
def create():
    user = CreateUser(request.get_json()).execute()
    return jsonify(user), 201


@users_blueprint.route('/users/<id>', methods=['PATCH'])
def update(id):
    response = UpdateUser(id, request.get_json()).execute()
    return jsonify(response)


@users_blueprint.route('/users/auth', methods=['POST'])
def auth():
    user = GenerateToken(request.get_json()).execute()
    return jsonify(user)


@users_blueprint.route('/users/me', methods=['GET'])
def show():
    user = GetUser(auth_token()).execute()
    return jsonify(user)


@users_blueprint.route('/empresas', methods=['GET'])
def get_empresas():
    print('hola')
    token = auth_token()
    if not token:
        return '', 401
    host = os.environ['EMPRESAS_PATH']
    url = f'{host}/empresas'
    response = requests.get(url)
    print(response.status_code)
    return response.content, response.status_code


@users_blueprint.route('/empresas', methods=['POST'])
def create_empresa():
    token = auth_token()
    headers = {'Content-Type': 'application/json'}
    if not token:
        return '', 401
    host = os.environ['EMPRESAS_PATH']
    url = f'{host}/empresas'
    body = request.get_json()
    json_payload = json.dumps(body)
    response = requests.post(url, data=json_payload, headers=headers)
    return response.content, response.status_code


@users_blueprint.route('/pruebas', methods=['GET'])
def get_pruebas():
    token = auth_token()
    if not token:
        return '', 401
    host = os.environ['PRUEBAS_PATH']
    url = f'{host}/pruebas'
    response = requests.get(url)
    return response.content, response.status_code


@users_blueprint.route('/pruebas', methods=['POST'])
def create_prueba():
    token = auth_token()
    headers = {'Content-Type': 'application/json'}
    if not token:
        return '', 401
    host = os.environ['PRUEBAS_PATH']
    url = f'{host}/pruebas'
    body = request.get_json()
    json_payload = json.dumps(body)
    response = requests.post(url, data=json_payload, headers=headers)
    return response.content, response.status_code


@users_blueprint.route('/proyectos', methods=['GET'])
def get_proyectos():
    token = auth_token()
    if not token:
        return '', 401
    host = os.environ['PROYECTOS_PATH']
    url = f'{host}/proyectos'
    response = requests.get(url)
    return response.content, response.status_code


@users_blueprint.route('/proyectos', methods=['POST'])
def create_proyecto():
    token = auth_token()
    headers = {'Content-Type': 'application/json'}
    if not token:
        return '', 401
    host = os.environ['PROYECTOS_PATH']
    url = f'{host}/proyectos'
    body = request.get_json()
    json_payload = json.dumps(body)
    response = requests.post(url, data=json_payload, headers=headers)
    return response.content, response.status_code


@users_blueprint.route('/aspirantes', methods=['GET'])
def get_aspirantes():
    token = auth_token()
    if not token:
        return '', 401
    host = os.environ['ASPIRANTES_PATH']
    url = f'{host}/aspirantes'
    response = requests.get(url)
    return response.content, response.status_code


@users_blueprint.route('/aspirantes', methods=['POST'])
def create_aspirantes():
    token = auth_token()
    headers = {'Content-Type': 'application/json'}
    if not token:
        return '', 401
    host = os.environ['ASPIRANTES_PATH']
    url = f'{host}/aspirantes'
    body = request.get_json()
    json_payload = json.dumps(body)
    response = requests.post(url, data=json_payload, headers=headers)
    return response.content, response.status_code


@users_blueprint.route('/users/ping', methods=['GET'])
def ping():
    return 'pong'


@users_blueprint.route('/users/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})


def auth_token():
    if 'Authorization' in request.headers:
        authorization = request.headers['Authorization']
    else:
        authorization = None
    return authorization
