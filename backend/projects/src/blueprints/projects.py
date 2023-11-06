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