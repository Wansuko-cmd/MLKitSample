if [ $# -ne 2 ]; then
  echo "使用方法：./setup.sh [プロジェクト名] [パッケージ名]"
  exit 1
fi

APP_NAME="$1"
PACKAGE_NAME="$2"

#############################
# パッケージファイルの置換
#############################
TARGET=$(find . |
     grep 'com/template$' |
     grep -v 'build' |
     xargs dirname | xargs dirname)
PACKAGE_NAME_WITH_SLASH=${PACKAGE_NAME//.//}

for DIR in $TARGET; do
  echo "$DIR/$PACKAGE_NAME_WITH_SLASH" | xargs mkdir -p
  mv "$DIR/com/template"/* "$DIR/$PACKAGE_NAME_WITH_SLASH"
  if [ -z "$(ls "$DIR"/com/template)" ]; then
    rm -r "$DIR/com/template"
  fi
  if [ -z "$(ls "$DIR"/com)" ]; then
    rm -r "$DIR"/com
  fi
done

#############################
# ファイル内に書かれたパッケージ名の置換
#############################
TARGET=$(find . -type f | grep -v 'build/' | grep -v '\/\.' | grep -v './setup.sh')

for DIR in $TARGET; do
  echo "$DIR"
  sed -i -e "s/Template/$APP_NAME/g" "$DIR"
  sed -i -e "s/com.template/$PACKAGE_NAME/g" "$DIR"
done

#############################
# Gitファイルの再生成
#############################
rm -rf .git
git init
git add .
git commit -m "first commit"
rm setup.sh