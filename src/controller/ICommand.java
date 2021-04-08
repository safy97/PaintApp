package controller;

interface ICommand {
    void execute();
    void undoExecute();
}
