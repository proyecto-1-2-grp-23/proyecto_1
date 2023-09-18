from flask import Flask, jsonify, request, Blueprint
from ..commands.create_pruebas import CreatePrueba
from ..commands.get_pruebas import GetPruebas
from ..commands.reset import Reset

pruebas_blueprint = Blueprint('pruebas', __name__)


@pruebas_blueprint.route('/pruebas', methods=['POST'])
def create():
    offer = CreatePrueba(request.get_json()).execute()
    return jsonify(offer), 201


@pruebas_blueprint.route('/pruebas', methods=['GET'])
def index():
    pruebas = GetPruebas(request.args.to_dict()).execute()
    return jsonify(pruebas)


@pruebas_blueprint.route('/pruebas/ping', methods=['GET'])
def ping():
    return 'pong'


@pruebas_blueprint.route('/pruebas/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})
