from flask import Flask, jsonify, request, Blueprint
from ..commands.create_proyectos import CreateProyecto
from ..commands.get_proyectos import GetProyectos
from ..commands.reset import Reset

proyectos_blueprint = Blueprint('proyectos', __name__)


@proyectos_blueprint.route('/proyectos', methods=['POST'])
def create():
    offer = CreateProyecto(request.get_json()).execute()
    return jsonify(offer), 201


@proyectos_blueprint.route('/proyectos', methods=['GET'])
def index():
    proyectos = GetProyectos(request.args.to_dict()).execute()
    return jsonify(proyectos)


@proyectos_blueprint.route('/proyectos/ping', methods=['GET'])
def ping():
    return 'pong'


@proyectos_blueprint.route('/proyectos/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})
