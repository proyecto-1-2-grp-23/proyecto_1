import sys
sys.path.append(".")

from ..commands.list_projects_data_laboral import ListProyectsDataLaboral
from ..commands.list_proyects_candidatos import ListProyectCandidato
from ..commands.list_projects import ListProjects
from ..commands.create_project import CreateProject
from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset

projects_blueprint = Blueprint('projects', __name__)


@projects_blueprint.route('/projects/ping', methods=['GET'])
def ping():
    return 'pong'

@projects_blueprint.route("/projects", methods=["POST"])
def create():
    proyect = CreateProject(request.get_json()).execute()
    return jsonify(proyect), 201

@projects_blueprint.route('/projects/listar-projects', methods=['GET'])
def listarTodos():
    equipo = ListProjects().execute()
    return jsonify(equipo), 200

@projects_blueprint.route("/projects/candidatos", methods=["GET"])
def list():
    candidatos = ListProyectCandidato().execute()
    return jsonify(candidatos), 200

@projects_blueprint.route('/projects/dataLaboral', methods=['GET'])
def listDataLaboral():
    catacteristicas = ListProyectsDataLaboral().execute()
    return jsonify(catacteristicas), 200

@projects_blueprint.route('/projects/reset', methods=['POST'])
def reset():
    Reset().execute()
    return jsonify({'status': 'OK'})


def auth_token():
    if 'Authorization' in request.headers:
        authorization = request.headers['Authorization']
    else:
        authorization = None
    return authorization