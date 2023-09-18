from flask import Flask, jsonify
from .session import Session, engine
from .models.model import Base
from .blueprints.proyectos import proyectos_blueprint
from dotenv import load_dotenv, find_dotenv
loaded = load_dotenv('.env.development')


app = Flask(__name__)
app.register_blueprint(proyectos_blueprint)

Base.metadata.create_all(engine)
