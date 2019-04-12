package com.telegrambot.bot;

import com.telegrambot.officialrates.OfficialRates;
import com.telegrambot.officialrates.OfficialRatesImpl;
import com.telegrambot.officialrates.OverTime;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bot extends TelegramLongPollingBot {
    private Update updateBetta;

    public void setUpdateBetta(Update updateBetta) {
        this.updateBetta = updateBetta;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
//        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
    public void onUpdateReceived(Update update) {
        SimpleDateFormat formatdate = new SimpleDateFormat("dd.MM.yyyy");

        setUpdateBetta(update);
        officialrates(updateBetta);

        Message message = update.getMessage();

        String datecoursebot = null;
        String currencybot = null;

        OverTime overTime = new OverTime();

        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/start":
                    sendMsg(message, "Добрый день, выберите\n"+
                            "валюту или узнайте курс\n"+
                            "за определенное время\n"+
                            "нажав на OVER TIME");
                    break;
                case "OVER TIME":
                    sendMsg(message, "Пример ввода:\n" + "дд.мм.гггг ДОЛЛАР США\n"+
                            "дд.мм.гггг ЕВРО\n" + "дд.мм.гггг РОССИЙСКИЙ РУБЛЬ\n"+
                            "дд.мм.гггг КИТАЙСКИЙ ЮАНЬ\n" + "дд.мм.гггг КЫРГЫЗСКИЙ СОМ\n"+
                            "Промежуток времени:\n" + "01.04.2003 - "+formatdate.format(new Date()));
                    break;
                default:
                    Pattern pattern = Pattern.compile("(\\d{2}\\.\\d{2}\\.\\d{4}) (.+)");
                    Matcher m = pattern.matcher(message.getText());
                    boolean b = m.matches();
                    b:
                    datecoursebot = m.group(1);
                    currencybot = m.group(2);

                    try {
                        overTime.setDatecourse(datecoursebot);
                        overTime.setCurrency(currencybot);

                        sendMsg(message, overTime.courseovertime());
                    } catch (KeyManagementException e) {
                        e.printStackTrace();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
            }
        }

    }

    public void officialrates(Update update){

        OfficialRates officialRates = new OfficialRatesImpl();
        Message message = update.getMessage();
        if (message != null && message.hasText()){
            switch (message.getText()){
                case "USD":
                    sendMsg(message, officialRates.courseUSD());
                    break;
                case "EUR":
                    sendMsg(message, officialRates.courseEUR());
                    break;
                case "RUB":
                    sendMsg(message, officialRates.courseRUB());
                    break;
                case "CNY":
                    sendMsg(message, officialRates.courseCNY());
                    break;
                case "KGS":
                    sendMsg(message, officialRates.courseKGS());
            }
        }
    }

    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<KeyboardRow>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("USD"));
        keyboardFirstRow.add(new KeyboardButton("EUR"));
        keyboardFirstRow.add(new KeyboardButton("RUB"));
        keyboardFirstRow.add(new KeyboardButton("CNY"));
        keyboardFirstRow.add(new KeyboardButton("KGS"));
        keyboardFirstRow.add(new KeyboardButton("OVER TIME"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

    }
    public String getBotUsername() {
        return "coursesKZbot";
    }

    public String getBotToken() {
        return "869176720:AAGEquiInrxZ6u-e8ZLpryp3v45UQ6nYwWA";
    }
}
