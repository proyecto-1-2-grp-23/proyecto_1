
from flask import Flask, jsonify, request, Blueprint
from ..session import Session, engine
from ..commands.list_candidatos import ListCandidato
from ..commands.list_data_laboral import ListDataLaboral
from ..commands.list_candidato_habilidades_tecnicas import (
    ListCandidatoHabTec,
)
from ..commands.list_candidato_recomendado import ListCandidatoRecomendado
from ..commands.list_candidato_habilidades_personalidad import (
    ListCandidatoHabPer,
)
from ..commands.list_candidato_by_id import (
    ListCandidatoById,
)


session = Session(bind=engine)
candidatos_blueprint = Blueprint("candidatos", __name__)


@candidatos_blueprint.route("/users/candidatos/ping", methods=["GET"])
def ping():
    return "pongs"


@candidatos_blueprint.route("/users/candidatos", methods=["GET"])
def list():
    candidatos = ListCandidato().execute()
    return jsonify(candidatos), 200


@candidatos_blueprint.route("/users/candidatos/<int:id>", methods=["GET"])
def listById(id):
    candidatos = ListCandidatoById(id).execute()
    return jsonify(candidatos), 200


@candidatos_blueprint.route("/users/candidatos/data-laboral", methods=["GET"])
def listDataLaboral():
    dataLaboral = ListDataLaboral().execute()
    return jsonify(dataLaboral), 200


@candidatos_blueprint.route(
    "/users/candidatos/caracteristicas-tecnicas/<string:tecnica>",
    methods=["GET"],
)
def ListCandidatosHabilidadesTecnicas(tecnica):
    candidatosTec = ListCandidatoHabTec(tecnica).execute()
    return jsonify(candidatosTec), 201


@candidatos_blueprint.route(
    "/users/candidatos/caracteristicas-personalidad/<string:personalidad>",
    methods=["GET"],
)
def ListCandidatosHabilidadesPersonalidad(personalidad):
    candidatosPer = ListCandidatoHabPer(personalidad).execute()
    return jsonify(candidatosPer), 201

@candidatos_blueprint.route("/users/candidatos/recomendados",methods=["GET"])
def ListCandidatosRecomendados():
    tecnica = request.args.get('tecnica', type=str)
    personalidad = request.args.get('personalidad', type=str)
    candidatos = ListCandidatoRecomendado(tecnica,personalidad).execute()
    
    return jsonify(candidatos), 200