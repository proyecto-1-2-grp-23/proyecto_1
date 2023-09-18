from flask import Flask, jsonify, request, Blueprint
from ..commands.create_aspirante import CreateAspirante
from ..commands.get_aspitante import GetAspirantes
from ..commands.reset import Reset

aspirantes_blueprint = Blueprint('aspirantes', __name__)


@aspirantes_blueprint.route('/aspirantes', methods=['POST'])
def create():
    offer = CreateAspirante(request.get_json()).execute()
    return jsonify(offer), 201


@aspirantes_blueprint.route('/aspirantes', methods=['GET'])
def index():
    aspirantes = GetAspirantes(request.args.to_dict()).execute()
    return jsonify(aspirantes)


@aspirantes_blueprint.route('/aspirantes/ping', methods=['GET'])
def ping():
    return 'pong'


@aspirantes_blueprint.route('/aspirantes/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})
