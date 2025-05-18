import { StyleSheet, Text, TouchableOpacity, View } from "react-native";
import { StatusBar } from "expo-status-bar";
import React, { useEffect } from "react";
import { COTTONBRO } from "@/assets/images";
import { ImageBackground } from "expo-image";
import { SplashScreen } from "expo-router";
import { useFonts, Poppins_700Bold } from '@expo-google-fonts/poppins';

const StartScreen = () => {
    let [fontLoaded] = useFonts({ Poppins_700Bold })

    if (!fontLoaded) {
        return null;
    } else {
        return (
            <View style={styles.container} >
                <StatusBar style="inverted" backgroundColor="lightgray" />
                <ImageBackground
                    style={styles.container}
                    source={COTTONBRO}
                >
                    <View style={styles.titleContainer}>
                        <Text>
                            <Text style={[styles.title, { fontFamily: 'Poppins_700Bold' }]}>{'exitto\n'}</Text>
                            <Text style={[styles.subTitle, { fontFamily: 'Poppins_700Bold' }]}>{' Manage your health with ease'}</Text>
                        </Text>
                    </View>
                    <View style={styles.card}>
                        <View style={styles.buttonContainer}>
                            <TouchableOpacity
                                activeOpacity={0.8}
                                style={styles.button}>
                                <Text style={[styles.textButton, { fontFamily: 'Poppins_700Bold' }]}>
                                    Get Started
                                </Text>
                            </TouchableOpacity>
                        </View>
                    </View>
                </ImageBackground>
            </View>
        );
    }
};

const styles = StyleSheet.create({
    container: {
        flex: 1
    },
    titleContainer: {
        paddingHorizontal: 22,
        paddingTop: 62
    },
    title: {
        fontSize: 92,
        color: 'lightgray',
    },
    subTitle: {
        fontSize: 20,
        color: 'lightgray',
        paddingLeft: 16
    },
    card: {
        backgroundColor: 'white',
        position: 'absolute',
        bottom: 0,
        height: 200,
        width: '100%',
        borderTopLeftRadius: 62,
        borderTopRightRadius: 62,
    },
    buttonContainer: {
        alignItems: 'center',
        justifyContent: 'center',
        marginHorizontal: 42,
        marginVertical: 100,
    },
    button: {
        width: '100%',
        height: 76,
        backgroundColor: 'gray',
        borderRadius: 100
    },
    textButton: {
        color: 'white',
        fontSize: 32,
        height: '100%',
        textAlign: 'center',
        textAlignVertical: 'center',
    }
});

export default StartScreen;