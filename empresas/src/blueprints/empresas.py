from flask import Flask, jsonify, request, Blueprint
from ..commands.create_empresas import CreateEmpresa
from ..commands.get_empresas import GetEmpresas
from ..commands.reset import Reset

empresas_blueprint = Blueprint('empresas', __name__)


@empresas_blueprint.route('/empresas', methods=['POST'])
def create():
    offer = CreateEmpresa(request.get_json()).execute()
    return jsonify(offer), 201


@empresas_blueprint.route('/empresas', methods=['GET'])
def index():
    empresas = GetEmpresas(request.args.to_dict()).execute()
    return jsonify(empresas)


@empresas_blueprint.route('/empresas/ping', methods=['GET'])
def ping():
    return 'pong'


@empresas_blueprint.route('/empresas/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})
