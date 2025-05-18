import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { StatusBar } from "expo-status-bar";
import React from "react";
import { COTTONBRO } from "@/assets/images";
import { ImageBackground } from "expo-image";

export default function Index() {
  return (
    <View style={styles.container} >
      <StatusBar style="inverted" backgroundColor="lightgray" />
      <ImageBackground
        style={styles.container}
        source={COTTONBRO}
      >
        <View style={styles.titleContainer}>
          <Text style={styles.title}>exitto</Text>
          <Text style={styles.subtitle}>Manage your health with ease</Text>
        </View>
      </ImageBackground>
      <View style={styles.card}>
        <View style={styles.buttonContainer}>
          <TouchableOpacity
            activeOpacity={0.8}
            style={styles.button}>
            <Text style={styles.textButton}>
              Get Started
            </Text>
          </TouchableOpacity>
        </View>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1.8
  },
  titleContainer: {
    paddingHorizontal: 22,
    paddingTop: 32
  },
  title: {
    fontSize: 92,
    fontWeight: '700',
    color: 'lightgray'
  },
  subtitle: {
    fontSize: 22,
    fontWeight: '700',
    color: 'lightgray',
    paddingStart: 4
  },
  card: {
    backgroundColor: 'white',
    transform: [{ translateY: (-72) }],
    flex: 0.25,
    flexDirection: 'column',
    borderTopLeftRadius: 62,
    borderTopRightRadius: 62,
  },
  buttonContainer: {
    alignItems: 'center',
    flex: 1,
    alignContent: 'flex-end',
    justifyContent: 'center',
    marginHorizontal: 16,
    marginVertical: 100,
  },
  button: {
    width: '100%',
    height: 70,
    justifyContent: 'center',
    backgroundColor: 'gray',
    borderRadius: 100
  },
  textButton: {
    color: 'white',
    textAlign: 'center',
    fontSize: 32,
    fontWeight: '700',
  }
})