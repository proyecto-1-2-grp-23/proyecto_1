import bcrypt

# Crear una contraseña
password = b'dffgfdgd'

# Generar un salt
salt = bcrypt.gensalt()
print(salt)

# Calcular el hash de la contraseña con el salt
hashed_password = bcrypt.hashpw(password, salt)
print(hashed_password)

# La variable 'hashed_password' ahora contiene el hash de la contraseña

# Para verificar la contraseña
user_input_password = b'dffgfdgd'  # La contraseña proporcionada por el usuario

# Verificar la contraseña ingresada con el hash almacenado
if bcrypt.checkpw(user_input_password, hashed_password):
    print("La contraseña es válida")
else:
    print("La contraseña es incorrecta")