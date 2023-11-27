import sys


sys.path.append(".")
from ..commands.list_projects import ListProjects
from ..commands.create_project import CreateProject
from ..commands.update_proyect import UpdateProject
from ..commands.create_proyecto_candidato import CreateProjectCandidato
from ..commands.list_proyects_empresa import ListProjectsEmpresa
from ..commands.list_project import ListProject
from ..commands.get_proyecto_candidato import ListCandidatosProyecto
from ..commands.list_proyects_candidato import ListProjectsCandidato
from flask import Flask, jsonify, request, Blueprint
from ..commands.reset import Reset

projects_blueprint = Blueprint("projects", __name__)


@projects_blueprint.route("/projects/ping", methods=["GET"])
def ping():
    return "pong"


@projects_blueprint.route("/projects", methods=["POST"])
def create():
    proyect = CreateProject(request.get_json()).execute()
    return jsonify(proyect), 201


@projects_blueprint.route("/projects/<int:id>", methods=["PUT"])
def update(id):
    proyect = UpdateProject(request.get_json(), id).execute()
    return jsonify(proyect), 200


@projects_blueprint.route("/projects/listar-projects", methods=["GET"])
def listarTodos():
    proyecto = ListProjects().execute()
    return jsonify(proyecto), 200


@projects_blueprint.route("/projects/listar-projects/<int:idEmpresa>", methods=["GET"])
def listarPorEmpresa(idEmpresa):
    proyecto = ListProjectsEmpresa(idEmpresa).execute()
    return jsonify(proyecto), 200


@projects_blueprint.route(
    "/projects/listar-candidatos/<int:idProyecto>", methods=["GET"]
)
def listarCandidatosProyecto(idProyecto):
    candidatos = ListCandidatosProyecto(idProyecto).execute()
    return jsonify(candidatos), 200


@projects_blueprint.route("/projects/listar-project/<int:idProyecto>", methods=["GET"])
def listarProyecto(idProyecto):
    proyecto = ListProject(idProyecto).execute()
    return jsonify(proyecto), 200


@projects_blueprint.route(
    "/projects/listar-projects/candidato/<int:idCandidato>", methods=["GET"]
)
def listarProyectosDeCandidato(idCandidato):
    proyectos = ListProjectsCandidato(idCandidato).execute()
    return jsonify(proyectos), 200


@projects_blueprint.route("/projects/proyecto-candidato", methods=["POST"])
def createProyectoCandidato():
    equipo = CreateProjectCandidato(request.get_json()).execute()
    return jsonify(equipo), 201


@projects_blueprint.route("/projects/reset", methods=["POST"])
def reset():
    Reset().execute()
    return jsonify({"status": "OK"})


def auth_token():
    if "Authorization" in request.headers:
        authorization = request.headers["Authorization"]
    else:
        authorization = None
    return authorization
