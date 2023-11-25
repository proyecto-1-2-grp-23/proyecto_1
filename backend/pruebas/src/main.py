from dotenv import load_dotenv, find_dotenv

loaded = load_dotenv(".env.development")

from .errors.errors import ApiError
from .blueprints.pruebas import pruebas_blueprint
from .models.model import Base
from .session import engine
from flask import Flask, jsonify
from flask_cors import CORS


app = Flask(__name__)
app.register_blueprint(pruebas_blueprint)
Base.metadata.create_all(engine)

cors = CORS(app)


@app.errorhandler(ApiError)
def handle_exception(err):
    response = {"msg": err.description}
    return jsonify(response), err.code
