from dotenv import load_dotenv, find_dotenv

loaded = load_dotenv(".env.development")

from .errors.errors import ApiError
from .blueprints.users import users_blueprint
from .blueprints.empresas import empresa_blueprint
from .blueprints.equipo import equipo_blueprint
from .blueprints.data_laboral import laboral_data_blueprint
from .blueprints.entrevistas import entrevistas_blueprint
from .blueprints.candidatos import candidatos_blueprint
from .models.model import Base
from .session import engine
from flask import Flask, jsonify
from flask_cors import CORS


app = Flask(__name__)
app.register_blueprint(users_blueprint)
app.register_blueprint(empresa_blueprint)
app.register_blueprint(equipo_blueprint)
app.register_blueprint(laboral_data_blueprint)
app.register_blueprint(entrevistas_blueprint)
app.register_blueprint(candidatos_blueprint)
Base.metadata.create_all(engine)

cors = CORS(app)
print(app.url_map)


@app.errorhandler(ApiError)
def handle_exception(err):
    response = {"msg": err.description}
    return jsonify(response), err.code
